import { Link } from 'react-router-dom';

function TrainersList({ trainers }) {
  return (
    <section>
      <h2>Trainers List</h2>
      <ul className="trainer-list">
        {trainers.map((trainer) => (
          <li key={trainer.TrainerId}>
            <Link to={`/trainers/${trainer.TrainerId}`}>{trainer.Name}</Link>
          </li>
        ))}
      </ul>
    </section>
  );
}

export default TrainersList;
