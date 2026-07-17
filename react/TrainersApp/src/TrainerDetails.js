import { useParams } from 'react-router-dom';
import trainers from './TrainersMock';

function TrainerDetail() {
  const { id } = useParams();
  const trainer = trainers.find(
    (currentTrainer) => currentTrainer.TrainerId === Number(id)
  );

  if (!trainer) {
    return <p>Trainer details are not available.</p>;
  }

  return (
    <section className="trainer-details">
      <h2>{trainer.Name}</h2>
      <dl>
        <dt>Trainer Id</dt>
        <dd>{trainer.TrainerId}</dd>
        <dt>Name</dt>
        <dd>{trainer.Name}</dd>
        <dt>Email</dt>
        <dd>{trainer.Email}</dd>
        <dt>Phone</dt>
        <dd>{trainer.Phone}</dd>
        <dt>Technology</dt>
        <dd>{trainer.Technology}</dd>
        <dt>Skills</dt>
        <dd>{trainer.Skills.join(', ')}</dd>
      </dl>
    </section>
  );
}

export default TrainerDetail;
