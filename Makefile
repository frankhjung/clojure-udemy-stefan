# Makefile for running Leiningen tasks under the `cicd` profile

LEIN = lein with-profile dev
LEIN_CICD = lein with-profile cicd

.PHONY: default clean fmt check compile test cicd-test cicd-clean show-profiles

default: clean fmt check compile test

#
# Local development targets
#

clean:
	$(LEIN) clean

fmt:
	$(LEIN) cljfmt fix

check:
	$(LEIN) cljfmt check

compile:
	$(LEIN) compile

test:
	$(LEIN) test

run:
	$(LEIN) run foo bar

#
# Targets for CI/CD pipelines (GitHub/GitLab)
#

cicd-clean:
	$(LEIN_CICD) clean

cicd-test:
	$(LEIN_CICD) eftest

#
# Utility target to show the :profiles section of project.clj for debugging purposes.
#
show-profiles:
	@echo "Project :profiles from project.clj:"
	@grep -n ":profiles" -A20 project.clj | sed 's/^/    /'
