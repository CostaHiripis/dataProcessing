package org.costandino.dataProcessing;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingMessage;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public abstract class GlobalRestHandler {

    public boolean isEntityValidJson(DomainEntity domainEntity, File jsonSchemaFile) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.byDefault();

        URI uri = jsonSchemaFile.toURI();
        JsonNode jsonNode = objectMapper.valueToTree(domainEntity);

        try
        {
            ProcessingReport report = jsonSchemaFactory.getJsonSchema(uri.toString()).validate(jsonNode);
            // print validation errors
            if (report.isSuccess())
            {
                return true;
            }
            else
            {
//                throw new RuntimeException()
                List<String> errors = new ArrayList<>();
                for (ProcessingMessage processingMessage: report) {
                    JsonNode jsonError = processingMessage.asJson();
                    var instance = jsonError.get("instance").findValue("pointer")
                            .asText().replaceFirst("/", "");
                    errors.add(instance + ": " + processingMessage.getMessage());
                }
                throw new SchemaException(errors.toString());
            }

        }
        catch (ProcessingException e)
        {
            e.printStackTrace();
        }
        return false;


    }
}
