# Week 1 — Engineering concepts

The two source trees contain the complete design-pattern and data-structure exercise set from the supplied PDFs. Each example retains its original runnable `main` method and package so it can also be opened independently in an IDE.

The Maven module adds all exercise source roots to one compile/test lifecycle:

```bash
../../mvnw -pl week-1/engineering-concepts test
```

The examples cover Singleton, Factory Method, Builder, Adapter, Decorator, Proxy, Observer, Strategy, Command, MVC, dependency injection, inventory management, linear/binary search, sorting, arrays, linked lists, and recursive financial forecasting. Complexity notes belong beside the relevant implementation and are checked during review.
