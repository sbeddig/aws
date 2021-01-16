import * as sns from '@aws-cdk/aws-sns';
import * as subs from '@aws-cdk/aws-sns-subscriptions';
import * as sqs from '@aws-cdk/aws-sqs';
import * as cdk from '@aws-cdk/core';
import * as api from '@aws-cdk/aws-apigateway';

export class InfrastructureStack extends cdk.Stack {
  constructor(scope: cdk.App, id: string, props?: cdk.StackProps) {
    super(scope, id, props);

    const queue = new sqs.Queue(this, 'InfrastructureQueue', {
      visibilityTimeout: cdk.Duration.seconds(300)
    });

    const topic = new sns.Topic(this, 'InfrastructureTopic');

    topic.addSubscription(new subs.SqsSubscription(queue));

    const helloWorldApi = new api.SpecRestApi(this, 'hello-world-api', {
      apiDefinition: api.ApiDefinition.fromAsset('../api/api.yaml')
    });
  }
}
