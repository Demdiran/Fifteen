
docker:
    stage: deploy
    image: docker:latest
    dependencies:
    script:
        - echo $dockerww | docker login -u demdiran --password-stdin
        - docker image build -t fifteen:1.1 .
        - docker image tag fifteen:1.1 demdiran/fifteen:1.1
        - docker image push demdiran/fifteen:1.1
    only:
        - master