package de.secretj12;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.*;
import java.util.UUID;

@Path("/AI")
public class AIResource {

    @POST
    @Path("/streetway")
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    @Produces(MediaType.TEXT_PLAIN)
    public String evaluatePic(InputStream in) throws IOException {
        System.out.println("Request");

        String filename = UUID.randomUUID().toString() + ".jpg";
        File f = new java.io.File("./" + filename);
        f.createNewFile();
        FileOutputStream fout = new FileOutputStream(f);
        fout.write(in.readAllBytes());
        fout.close();

        Runtime rt = Runtime.getRuntime();
        Process pr = rt.exec(new String[]{"python3.10", "Classification.py", filename});

        BufferedReader reader = pr.inputReader();
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.contains("Found classification:")) {
                System.out.println(line.split(" ")[2]);
                return line;
            }
        }
        return "No classification found";
    }

    public static class MultipartBody {
        @FormParam("file")
        @PartType(MediaType.APPLICATION_OCTET_STREAM)
        public InputStream file;
    }
}
