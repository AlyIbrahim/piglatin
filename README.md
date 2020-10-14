# PigLatin

A Java Quarkus application consists of 2 services:
- piglatin-bot : A slack bot receives slash commands, translate the text in the command pushes it to a kafka topic and respond back to the user with the translated text.
- piglatin-streamer : Stream data from the same kafka topic as source and streams it to a web page using server sent events

## Prerequists

The application uses kafka as the underlying message system, so you need to have a kafka cluster running with a topic, for the application to start working.
In this project I use a topic named `slack`, if you want to use a different name, you need to modify the code.
For Kafka running on Openshift/Kubernetes you can use Strimzi operator to build your kafka cluster and for Openshift you can install `AMQ Streams` from Operator Hub.

You may need to have a slack application created and installed to one of your workspaces and has the required permissions to respond to slash commands already, and once the application is live, you may need to configure the slash command url as: `http://<HOST-NAME>/slash`

**Note:** since this is for demo purposes, I chose to skip the sender identity, but you may need to implement that using provided tokens and appId provided by slack for production use.


## Piglatin Bot
The bot piece can be installed as a normal app or as a knative (serverless) app, if you wanna install it as serverelss app, you may consider the cold start since slack requires 3 sec response time, so probably your first request may fail until the pod is up, or you can avoid this by setting minimum number of instances to be 1.

For the app to connect to kafka, you will need to provide a configmap with the kafka broker url to the application, you can use the following command:

`oc create configmap --from-literal=KAFKA_BOOTSTRAP_SERVERS=$KAFKA_BOOTSTRAP_SERVICE piglatin -n $NAMESPACE`

once created you may need to attach it to the deployment using the following command:

`oc set env oc dc/piglatin-bot --from configmap/piglatin`

## Piglatin Streamer
The streamer piece listen to the same topic as source, so you need to provide connection to kafka as described in the previous step with Piglatin Bot, and if it runs in the same namespace you may just set it up as the config map is already there.

To start streaming what the users is sending, once the application is up, from your browser use the following url:
`http://<HOST_NAME>/stream.html`
