import { render, screen } from '@testing-library/react';
import { describe, expect, it } from 'vitest';

describe('test foundation', () => {
  it('renders a basic element with testing-library', () => {
    render(<button type="button">Run tests</button>);

    expect(screen.getByRole('button', { name: 'Run tests' })).toBeInTheDocument();
  });
});

