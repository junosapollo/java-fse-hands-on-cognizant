import React, { Component } from 'react';

export class Cart {
  constructor(Itemname, Price) {
    this.Itemname = Itemname;
    this.Price = Price;
  }
}

export class OnlineShopping extends Component {
  constructor(props) {
    super(props);
    this.cart = [
      new Cart('Laptop', 75000),
      new Cart('Headphones', 2500),
      new Cart('Keyboard', 1800),
      new Cart('Mouse', 900),
      new Cart('Monitor', 12000)
    ];
  }

  render() {
    return (
      <section className="shopping-app">
        <h1>Online Shopping Cart</h1>
        <ul>
          {this.cart.map((item) => (
            <li key={item.Itemname}>
              <span>{item.Itemname}</span> — ₹{item.Price}
            </li>
          ))}
        </ul>
      </section>
    );
  }
}

function App() {
  return <OnlineShopping />;
}

export default App;
