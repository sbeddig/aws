#!/usr/bin/env node
import * as cdk from '@aws-cdk/core';
import { S3LambdaStack } from '../lib/app';

const app = new cdk.App();
new S3LambdaStack(app, 'appStack');
