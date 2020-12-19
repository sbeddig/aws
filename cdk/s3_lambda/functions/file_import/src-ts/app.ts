import {S3CreateEvent} from "aws-lambda"

export const lambdaHandler = async (event: S3CreateEvent): Promise<string> => {
    console.log(event.Records[0].s3.object.key);
    return event.Records[0].s3.object.key;
}