provider "aws" {
  region                      = "us-west-2"
  access_key                  = "foo"
  secret_key                  = "bar"
  skip_credentials_validation = true
skip_requesting_account_id = true 

  endpoints {
    dynamodb = "http://localhost:4566"
  }
}

resource "aws_dynamodb_table" "url_key" {
  name           = "URL_KEY"
  read_capacity  = "5"
  write_capacity = "5"
  hash_key       = "URL_"

  attribute {
    name = "URL_"
    type = "S"
  }
}


resource "aws_dynamodb_table" "key_url" {
  name           = "KEY_URL"
  read_capacity  = "5"
  write_capacity = "5"
  hash_key       = "ID"

  attribute {
    name = "ID"
    type = "S"
  }
}
