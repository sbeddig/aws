#!/usr/bin/env node
import * as cdk from '@aws-cdk/core';
import { S3LambdaStack } from '../lib/s3_lambda-stack';

const app = new cdk.App();
new S3LambdaStack(app, 'S3LambdaStack');
