import * as cdk from '@aws-cdk/core';
import * as sns from '@aws-cdk/aws-sns';
import * as sqs from '@aws-cdk/aws-sqs';
import * as subscriptions from '@aws-cdk/aws-sns-subscriptions'

export class CdkStack extends cdk.Stack {
  constructor(scope: cdk.Construct, id: string, props?: cdk.StackProps) {
    super(scope, id, props);

    const topic = new sns.Topic(this, "testTopic", {
      topicName: "testTopic"
    })

    const queue = new sqs.Queue(this, "testQueue", {
      queueName: "testQueue",
    })

    topic.addSubscription(new subscriptions.SqsSubscription(queue))

  }
}
