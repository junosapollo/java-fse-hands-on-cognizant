import Trainer from './trainer';

const trainers = [
  new Trainer(
    1,
    'John',
    'john@cognizant.com',
    '9876543210',
    'Java',
    ['Java', 'Spring Boot', 'Microservices']
  ),
  new Trainer(
    2,
    'Peter',
    'peter@cognizant.com',
    '9876543211',
    'React',
    ['HTML', 'CSS', 'JavaScript', 'React']
  ),
  new Trainer(
    3,
    'Arun',
    'arun@cognizant.com',
    '9876543212',
    'DevOps',
    ['Git', 'Docker', 'Kubernetes']
  )
];

export default trainers;
