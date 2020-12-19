import { S3CreateEvent } from "aws-lambda";
export declare const lambdaHandler: (event: S3CreateEvent) => Promise<string>;
