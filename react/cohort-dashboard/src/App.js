import CohortDetails from './components/CohortDetails';

const cohorts = [
  {
    name: 'INTADMDF10 - .NET FSD',
    startedOn: '22-Feb-2022',
    status: 'scheduled',
    coach: 'Mounika',
    trainer: 'John'
  },
  {
    name: 'ADM21JF014 - Java FSD',
    startedOn: '10-Sep-2021',
    status: 'ongoing',
    coach: 'Apoorv',
    trainer: 'Priya'
  },
  {
    name: 'CDBJF21025 - Java FSD',
    startedOn: '24-Dec-2021',
    status: 'completed',
    coach: 'Ravi',
    trainer: 'Kavitha'
  }
];

function App() {
  return (
    <main className="dashboard">
      <h1>Cohorts Details</h1>
      {cohorts.map((cohort) => (
        <CohortDetails cohort={cohort} key={cohort.name} />
      ))}
    </main>
  );
}

export default App;
