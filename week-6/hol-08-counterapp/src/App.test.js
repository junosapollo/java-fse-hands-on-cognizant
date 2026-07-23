import { fireEvent, render, screen } from '@testing-library/react';
import App from './App';

test('increments entry and exit counters independently', () => {
  render(<App />);
  fireEvent.click(screen.getByRole('button', { name: 'Login' }));
  fireEvent.click(screen.getByRole('button', { name: 'Login' }));
  fireEvent.click(screen.getByRole('button', { name: 'Exit' }));
  expect(screen.getByTestId('entry-count').textContent).toBe('2');
  expect(screen.getByTestId('exit-count').textContent).toBe('1');
});
