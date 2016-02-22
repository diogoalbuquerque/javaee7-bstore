package br.com.livraria.loja.infra;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.S3ClientOptions;

import javax.enterprise.inject.Produces;

public class AmazonS3ClientProducer {

    @Produces
    public AmazonS3Client s3Ninja() {
        AWSCredentials credentials = new BasicAWSCredentials(
                "IDENTIDADE1",
                "IDENTIDADE2");
        AmazonS3Client newClient = new AmazonS3Client(credentials,
                new ClientConfiguration());
        newClient.setS3ClientOptions(new S3ClientOptions()
                .withPathStyleAccess(true));
        newClient.setEndpoint("http://localhost:9444/s3");
        return newClient;

    }
}
