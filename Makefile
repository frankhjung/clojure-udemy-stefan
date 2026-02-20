# Makefile for running Leiningen tasks under the `cicd` profile

LEIN = lein with-profile cicd

.PHONY: all default clean check compile test show-profiles run

default: clean check compile test

all: default run

clean:
	$(LEIN) clean

check:
	$(LEIN) check

compile:
	$(LEIN) compile

test:
	$(LEIN) test

# Continuous test runner (detailed failures, auto-rerun on file change)
test-refresh:
	$(LEIN) test-refresh

run:
	$(LEIN) run foo bar

show-profiles:
	@echo "Project :profiles from project.clj:"
	@sed -n '/:profiles/,/}/p' project.clj
