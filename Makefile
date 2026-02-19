# Makefile for running Leiningen tasks under the `cicd` profile

LEIN = lein with-profile cicd

.PHONY: default clean check compile test show-profiles run

default: clean check compile test

clean:
	$(LEIN) clean

check:
	$(LEIN) check

compile:
	$(LEIN) compile

test:
	$(LEIN) test

run:
	$(LEIN) run -m tutorial.chapter2 "foo" "bar"

show-profiles:
	@echo "Project :profiles from project.clj:"
	@sed -n '/:profiles/,/}/p' project.clj
