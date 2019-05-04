package net.fender.aws;

import net.fender.jda.TokenProvider;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.GetParameterRequest;
import software.amazon.awssdk.services.ssm.model.GetParameterResponse;

import javax.inject.Inject;

@Component
public class SSMTokenProvider implements TokenProvider {

    private final SsmClient ssm;

    @Inject
    public SSMTokenProvider(SsmClient ssm) {
        this.ssm = ssm;
    }

    @Override
    public String getToken() {
        GetParameterRequest request = GetParameterRequest.builder().
                name("/discord/token").
                withDecryption(Boolean.TRUE).
                build();
        GetParameterResponse response = ssm.getParameter(request);
        return response.parameter().value();
    }
}
