package net.fender.aws;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.*;
import software.amazon.awssdk.services.ssm.SsmClient;

@Configuration
@EnableConfigurationProperties(AwsProperties.class)
public class SSMConfiguration {

    @Bean
    public AwsCredentialsProvider credentialsProvider(AwsProperties props) {
        String accessKeyId = props.getAccessKeyId();
        String secretAccessKey = props.getSecretAccessKey();
        if (accessKeyId != null && secretAccessKey != null) {
            AwsBasicCredentials credentials = AwsBasicCredentials.create(accessKeyId, secretAccessKey);
            return StaticCredentialsProvider.create(credentials);
        }

        String profileName = props.getProfileName();
        if (profileName != null) {
            return ProfileCredentialsProvider.create(profileName);
        }

        return DefaultCredentialsProvider.create();
    }

    @Bean
    public SsmClient ssmClient(AwsCredentialsProvider credentialsProvider) {
        return SsmClient.builder().credentialsProvider(credentialsProvider).build();
    }
}
