import { render, screen } from '@testing-library/react';
import App from './App';

test('renders trainer navigation and home route', () => {
  // App owns its BrowserRouter; wrapping it in another router causes the
  // React Router invariant to fail in the test environment.
  render(<App />);
  expect(screen.getByRole('link', { name: 'Show Trainers' })).toBeTruthy();
  expect(screen.getByRole('heading', { name: /welcome to trainers app/i })).toBeTruthy();
});
