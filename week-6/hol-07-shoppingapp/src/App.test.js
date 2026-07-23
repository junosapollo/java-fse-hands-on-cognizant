import { render, screen } from '@testing-library/react';
import App from './App';

test('renders five cart items', () => {
  render(<App />);
  expect(screen.getByRole('heading', { name: /online shopping cart/i })).toBeTruthy();
  expect(screen.getAllByRole('listitem')).toHaveLength(5);
  expect(screen.getByText(/laptop/i)).toBeTruthy();
});
