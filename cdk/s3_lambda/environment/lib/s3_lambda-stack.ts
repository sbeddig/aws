import * as cdk from '@aws-cdk/core';
import * as s3 from '@aws-cdk/aws-s3';
import * as s3n from '@aws-cdk/aws-s3-notifications';
import * as lambda from '@aws-cdk/aws-lambda';

export class S3LambdaStack extends cdk.Stack {
  constructor(scope: cdk.App, id: string, props?: cdk.StackProps) {
    super(scope, id, props);

    const importBucket = new s3.Bucket(this, 'importBucket', {
      versioned: false,
      bucketName: 'import-file-bucket',
      publicReadAccess: false,
      removalPolicy: cdk.RemovalPolicy.DESTROY
    });

    const importFunction = new lambda.Function(this, 'importFunction', {
      code: lambda.Code.fromAsset("../functions/file_import/dist"),
      runtime: lambda.Runtime.NODEJS_12_X,
      memorySize: 256,
      handler: "app.lambdaHandler",
      retryAttempts: 2
    });

    importBucket.grantReadWrite(importFunction);
    importBucket.addObjectCreatedNotification(new s3n.LambdaDestination(importFunction));
  }
}
