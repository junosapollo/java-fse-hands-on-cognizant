import '../Stylesheets/mystyle.css';

function CalculateScore(props) {
  const average = props.Total / props.Goal;

  return (
    <div className="score-card">
      <h1>Student Score Details</h1>
      <p>Name: {props.Name}</p>
      <p>School: {props.School}</p>
      <p>Total: {props.Total}</p>
      <p>Goal: {props.Goal}</p>
      <p className="average">Average Score: {average.toFixed(2)}</p>
    </div>
  );
}

export default CalculateScore;
