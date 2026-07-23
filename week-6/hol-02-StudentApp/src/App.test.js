import { render, screen } from '@testing-library/react';
import App from './App';

test('renders all student portal components', () => {
  render(<App />);
  expect(screen.getByText(/welcome to the home page/i)).toBeTruthy();
  expect(screen.getByText(/welcome to the about page/i)).toBeTruthy();
  expect(screen.getByText(/welcome to the contact page/i)).toBeTruthy();
});
