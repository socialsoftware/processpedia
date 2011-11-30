package pt.ist.processpedia.server;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileUploadServlet extends HttpServlet {

  private static final long serialVersionUID = -167657131746991345L;

  private static final String UPLOAD_DIRECTORY = "target/processpedia-client-1.0-SNAPSHOT/images/avatar/";

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setCharacterEncoding("UTF-8");
    resp.setContentType("application/json");
    if (!ServletFileUpload.isMultipartContent(req)) {
      resp.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
      resp.getWriter().print("{ \"message\": \"Request contents type is not supported by the servlet\" }");
      return;
    }
    FileItemFactory factory = new DiskFileItemFactory();
    ServletFileUpload upload = new ServletFileUpload(factory);

    try {
      List<FileItem> items = upload.parseRequest(req);

      for (FileItem item : items) {
        if (item.isFormField()) {
          System.out.println(item.getFieldName()+" - "+item.getString());
          continue;
        }

        String fileName = item.getName();
        File uploadedFile = new File(UPLOAD_DIRECTORY, fileName);
        if (uploadedFile.createNewFile()) {
          item.write(uploadedFile);
          resp.setStatus(HttpServletResponse.SC_CREATED);
          resp.getWriter().print("{ \"message\": \"File uploaded with success\" }");
          resp.flushBuffer();
        } else
          throw new IOException("The file already exists in repository.");
      }
    } catch(Exception e) {
      resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      resp.getWriter().print("{ \"message\": \"Error while trying to create the file\" }");
    }

  }

}
