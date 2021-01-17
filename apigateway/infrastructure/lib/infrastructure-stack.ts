import * as sns from '@aws-cdk/aws-sns';
import * as subs from '@aws-cdk/aws-sns-subscriptions';
import * as sqs from '@aws-cdk/aws-sqs';
import * as cdk from '@aws-cdk/core';
import * as api from '@aws-cdk/aws-apigateway';
import * as lambda from '@aws-cdk/aws-lambda';
import * as logs from '@aws-cdk/aws-logs';
import * as path from 'path';
import {CfnOutput} from '@aws-cdk/core';

export class InfrastructureStack extends cdk.Stack {
  constructor(scope: cdk.App, id: string, props?: cdk.StackProps) {
    super(scope, id, props);

    const queue = new sqs.Queue(this, 'InfrastructureQueue', {
      visibilityTimeout: cdk.Duration.seconds(300)
    });

    const topic = new sns.Topic(this, 'InfrastructureTopic');

    topic.addSubscription(new subs.SqsSubscription(queue));

    const functions = [
      { name: 'helloWorld' }
    ];

    for (const fn of functions) {
      new lambda.Function(this, fn.name, {
        functionName: fn.name,
        runtime: lambda.Runtime.GO_1_X,
        handler: 'main',
        code: lambda.Code.fromAsset(path.join(__dirname, `../../apps/build/${fn.name}.zip`)),
        logRetention: logs.RetentionDays.ONE_DAY
      });
    }

    const helloWorldApi = new api.SpecRestApi(this, 'hello-world-api', {
      apiDefinition: api.ApiDefinition.fromAsset('../api/spec.yaml'),
    });

    new CfnOutput(this, "Endpoint", {
      value: `http://localhost:4566/restapis/${helloWorldApi.restApiId}/prod/_user_request_/`
    })

  }
}
