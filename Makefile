# Makefile for running Leiningen tasks under the `cicd` profile

LEIN = lein with-profile cicd

.PHONY: default clean check compile test show-profiles

default: clean check compile test

clean:
	$(LEIN) clean

check:
	$(LEIN) check

compile:
	$(LEIN) compile

test:
	$(LEIN) test

show-profiles:
	@echo "Project :profiles from project.clj:"
	@sed -n '/:profiles/,/}/p' project.clj
