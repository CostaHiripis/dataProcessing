package org.costandino.dataProcessing.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingMessage;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import org.costandino.dataProcessing.domain.Agriculture.Agriculture;
import org.costandino.dataProcessing.domain.Agriculture.Agricultures;
import org.costandino.dataProcessing.domain.DomainEntity;
import org.costandino.dataProcessing.exception.SchemaException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.xml.sax.SAXException;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI;

@ControllerAdvice
public abstract class GlobalRestHandler {

    public boolean isEntityValidXml(DomainEntity entity, File xmlSchema) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            String xml;
            xml = xmlMapper.writeValueAsString(entity);
            xml = xml.replaceFirst("<agriculture>", "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n <agriculture xmlns=\"https://www.w3schools.com\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"https://www.w3schools.com agriculture_schema.xsd\">");
            SchemaFactory schemaFactory = SchemaFactory.newInstance(W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(xmlSchema);
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new ByteArrayInputStream(xml.getBytes((StandardCharsets.UTF_8)))));
            return true;
        } catch ( SAXException e) {
            throw new SchemaException(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();

        }
        return false;
    }

    public boolean isEntityValidJson(DomainEntity domainEntity, File jsonSchemaFile) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.byDefault();

        URI uri = jsonSchemaFile.toURI();
        JsonNode jsonNode = objectMapper.valueToTree(domainEntity);

        try
        {
            ProcessingReport report = jsonSchemaFactory.getJsonSchema(uri.toString()).validate(jsonNode);
            if (report.isSuccess())
            {
                return true;
            }
            else
            {
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

    public Agricultures getValidAgriculture(List<Agriculture> agricultureServiceResponse, File schema, String contentType) {
        List<Agriculture> validAgriculture = new ArrayList<>();
        Agricultures agricultures = new Agricultures();
        for (Agriculture agriculture: agricultureServiceResponse) {
            try {
                switch (contentType) {
                    case "application/json" -> isEntityValidJson(agriculture, schema);
                    case "application/xml" -> isEntityValidXml(agriculture, schema);
                }
                validAgriculture.add(agriculture);
            } catch (SchemaException e) {
                e.printStackTrace();
            }
        }
        agricultures.setAgriculture(validAgriculture);
        return agricultures;
    }
}
