#!/bin/bash

KAFKA_CONTAINER="docker_kafka_dev"

echo -e "kafka-topics.sh --create --topic first_topic"
docker exec -it $KAFKA_CONTAINER kafka-topics.sh --create --zookeeper 127.0.0.1:2181 --partitions 3 --replication-factor 1 --config cleanup.policy=compact --topic first_topic

echo -e "\nkafka-topics.sh --list"
docker exec -it $KAFKA_CONTAINER kafka-topics.sh --list --zookeeper 127.0.0.1:2181
