import { beforeEach, describe, expect, it, vi } from 'vitest';
import { createUserInBackend, getMe } from '@/api/userApi';
import type { ApiUser, RegisterPayload } from '@/types/auth';

// VITE_API_OFFER_URL is set to http://test-api via .env.test

// Replace global fetch with a Vitest spy before each test.
// We reset it before each test so mocks don't bleed across tests.
const mockFetch = vi.fn();
beforeEach(() => {
  vi.stubGlobal('fetch', mockFetch);
  mockFetch.mockReset();
});

// ─── Helper ───────────────────────────────────────────────────────────────────

/** Build a minimal fetch Response shape for tests. */
function makeResponse(body: unknown, status = 200): Response {
  return {
    ok: status >= 200 && status < 300,
    status,
    json: () => Promise.resolve(body),
  } as unknown as Response;
}

// ─── getMe ────────────────────────────────────────────────────────────────────

describe('getMe', () => {
  it('returns a typed ApiUser on success', async () => {
    const user: ApiUser = {
      userId: 1,
      firstName: 'Alice',
      lastName: 'Dupont',
      email: 'alice@example.com',
      groupId: 1,
    };
    mockFetch.mockResolvedValueOnce(makeResponse(user, 200));

    const result = await getMe('fake-token');

    expect(result).toEqual(user);
  });

  it('sends the Authorization header with the provided token', async () => {
    const user: ApiUser = {
      userId: 2,
      firstName: 'Bob',
      lastName: 'Martin',
      email: 'bob@example.com',
      groupId: 2,
    };
    mockFetch.mockResolvedValueOnce(makeResponse(user, 200));

    await getMe('my-id-token');

    expect(mockFetch).toHaveBeenCalledWith(
      'http://test-api/api/users/me',
      expect.objectContaining({
        headers: expect.objectContaining({
          Authorization: 'Bearer my-id-token',
        }),
      })
    );
  });

  it('throws with the response status when the request fails', async () => {
    mockFetch.mockResolvedValueOnce(makeResponse({ message: 'Unauthorized' }, 401));

    await expect(getMe('bad-token')).rejects.toMatchObject({
      message: 'Failed to fetch /me',
      status: 401,
    });
  });

  it('throws when the server returns a 500 error', async () => {
    mockFetch.mockResolvedValueOnce(makeResponse({ message: 'Server error' }, 500));

    await expect(getMe('any-token')).rejects.toMatchObject({
      status: 500,
    });
  });
});

// ─── createUserInBackend ──────────────────────────────────────────────────────

describe('createUserInBackend', () => {
  const payload: RegisterPayload = {
    email: 'carol@example.com',
    password: 'secret',
    firstName: 'Carol',
    lastName: 'Smith',
  };

  it('sends a PUT request to /api/users with the token and uid', async () => {
    mockFetch.mockResolvedValueOnce(makeResponse({}, 200));

    await createUserInBackend('tok', 'uid-123', payload);

    expect(mockFetch).toHaveBeenCalledWith(
      'http://test-api/api/users',
      expect.objectContaining({
        method: 'PUT',
        headers: expect.objectContaining({
          Authorization: 'Bearer tok',
          'Content-Type': 'application/json',
        }),
        body: expect.stringContaining('"idpId":"uid-123"'),
      })
    );
  });

  it('includes the email from the payload in the request body', async () => {
    mockFetch.mockResolvedValueOnce(makeResponse({}, 200));

    await createUserInBackend('tok', 'uid-abc', payload);

    const body = JSON.parse((mockFetch.mock.calls[0][1] as RequestInit).body as string);
    expect(body.email).toBe('carol@example.com');
    expect(body.idpId).toBe('uid-abc');
  });

  it('resolves without throwing when the request succeeds', async () => {
    mockFetch.mockResolvedValueOnce(makeResponse({}, 201));

    await expect(createUserInBackend('tok', 'uid-xyz', payload)).resolves.toBeUndefined();
  });
});
