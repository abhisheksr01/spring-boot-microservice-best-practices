.DEFAULT_GOAL := help
CI_TEAM := main

define fly-status
	fly -t ${CI_TEAM} status
endef

.PHONY: help
# See <https://gist.github.com/klmr/575726c7e05d8780505a> for explanation.
## This help screen
help:
	@echo "$$(tput bold)Available rules:$$(tput sgr0)";echo;sed -ne"/^## /{h;s/.*//;:d" -e"H;n;s/^## //;td" -e"s/:.*//;G;s/\\n## /---/;s/\\n/ /g;p;}" ${MAKEFILE_LIST}|LC_ALL='C' sort -f|awk -F --- -v n=$$(tput cols) -v i=19 -v a="$$(tput setaf 6)" -v z="$$(tput sgr0)" '{printf"%s%*s%s ",a,-i,$$1,z;m=split($$2,w," ");l=n-i;for(j=1;j<=m;j++){l-=length(w[j])+1;if(l<= 0){l=n-i-length(w[j])-1;printf"\n%*s ",-i," ";}printf"%s ",w[j];}printf"\n";}'

.PHONY: spin-concourse
## spin's concourse locally & is accessible at http://127.0.0.1:8080
spin-concourse:
	cd local-concourse; \
	docker compose up -d;

## spin's concourse locally for m1 laptops & is accessible at http://127.0.0.1:8080 for m1 macOS
spin-concourse-m1:
	cd local-concourse; \
	docker-compose -f docker-compose-m1.yml up -d;

.PHONY: destroy-concourse
## destroy concourse local
destroy-concourse:
	cd local-concourse; \
	docker compose down;

.PHONY: fly-target
## setup a fly target to the concourse server for this project
fly-target:
	@$(call fly-status) || fly -t ${CI_TEAM} login -c http://127.0.0.1:8080 -n ${CI_TEAM}

.PHONY: set-pipeline
## set the pipeline
set-pipeline: fly-target
	@fly -t ${CI_TEAM} validate-pipeline -c pipeline.yml
	@fly -t ${CI_TEAM} set-pipeline -p spring-boot-best-practices -c pipeline.yml -l ./secrets/vars.yml

.PHONY: destroy-pipeline
## destroy the pipeline
destroy-pipeline: fly-target
	@fly -t ${CI_TEAM} validate-pipeline -c pipeline.yml
	@fly -t ${CI_TEAM} destroy-pipeline -p spring-boot-best-practices

.PHONY: hijack
## hijack the pipeline job
hijack: fly-target
	@fly -t ${CI_TEAM} hijack -u http://127.0.0.1:8080/teams/main/pipelines/spring-boot-best-practices/jobs/${job_name}

