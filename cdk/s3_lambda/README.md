# Simple "CDK-S3-Lambda" Example

## Description

This is a very simple example using aws cdk to create a bucket, a lambda function, and a s3 bucket notification, to invoke the function, if a file was saved into the bucket. The lambda is written in Typescript.

File -> Bucket -> Lambda

The return value of the lambda is the saved filename.

## Execution

To deploy this example to aws, you can use the defined tasks inside the taskfile yml. Before you can use the tasks from the file, you have to install task (https://taskfile.dev/#/) and you must be logged in to aws.

Commands:
```shell
task             # build lambda and deploy to aws
task destroy     # destroy aws stack for this example
```

Please delete all files from the bucket, before you use the destroy task. Otherwise, the destroy task will end in an exception.