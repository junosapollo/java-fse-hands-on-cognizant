import { BrowserRouter, Link, Route, Routes } from 'react-router-dom';
import Home from './Home';
import TrainerDetail from './TrainerDetails';
import TrainersList from './Trainerlist';
import trainers from './TrainersMock';

function App() {
  return (
    <BrowserRouter>
      <main className="trainers-app">
        <nav className="nav">
          <Link to="/">Home</Link>
          <Link to="/trainers">Show Trainers</Link>
        </nav>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route
            path="/trainers"
            element={<TrainersList trainers={trainers} />}
          />
          <Route path="/trainers/:id" element={<TrainerDetail />} />
        </Routes>
      </main>
    </BrowserRouter>
  );
}

export default App;
