import { render, screen } from '@testing-library/react';
import App from './App';

test('renders cohort cards and status colors', () => {
  render(<App />);
  expect(screen.getByRole('heading', { name: /cohorts details/i })).toBeTruthy();
  expect(screen.getByText('ADM21JF014 - Java FSD')).toBeTruthy();
  expect(screen.getByText('ongoing')).toBeTruthy();
});
