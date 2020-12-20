import { expect as expectCDK, haveResource } from '@aws-cdk/assert';
import * as cdk from '@aws-cdk/core';
import * as S3Lambda from '../lib/s3_lambda-stack';

test('S3 Bucket Created', () => {
    const app = new cdk.App();
    // WHEN
    const stack = new S3Lambda.S3LambdaStack(app, 'MyTestStack');
    // THEN
    expectCDK(stack).to(haveResource("AWS::S3::Bucket",{
      BucketName: "import-bucket"
    }));
});

test('Lambda Created', () => {
  const app = new cdk.App();
  // WHEN
  const stack = new S3Lambda.S3LambdaStack(app, 'MyTestStack');
  // THEN
  expectCDK(stack).to(haveResource("AWS::Lambda::Function"));
});
