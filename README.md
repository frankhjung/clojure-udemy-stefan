# Udemy: Learn Clojure, a fun new functional programming language

These are my notes for [Clojure: The Complete Beginner's
Guide](https://www.udemy.com/course/clojureprogramming/) created by [Catalin
Stefan](https://www.udemy.com/user/catalinstefan2/).

The course material can be found on GitHub at
[github.com/CatalinStefan/Clojure](https://github.com/CatalinStefan/Clojure)

## Getting Started

This project uses [Leiningen](https://leiningen.org/) as the build tool. To run
the REPL, use `lein repl` in the terminal. To run the tests, use `lein test`. To
run the main function, use `lein run -m tutorial.core`. run the main function,
use `lein run -m tutorial.chapter2`.

## Calva

UseVSCode with the
[Calva extension](https://marketplace.visualstudio.com/items?itemName=betterthantomorrow.calva)
is recommended for Clojure development. It provides features like REPL
integration, code evaluation, and debugging.

## Building with Make

If you prefer to use `make` for common development tasks, a `Makefile` is
provided. You can typically run `make` commands from your terminal. For example:

```bash
make build
make test
make run foo bar
```

Please refer to the `Makefile` itself for a complete list of available commands.

## Run

The main function in `tutorial.core` generates random data and demonstrates the
functionality of the other chapters. You can run it using
`lein run -m tutorial.core` or `make run`.

## Resources

- [Clojure Documentation](https://clojure.org/documentation)
- [Clojure Cheatsheet](https://clojure.org/api/cheatsheet)
- [Course GitHub Repository](https://www.udemy.com/user/catalinstefan2)
