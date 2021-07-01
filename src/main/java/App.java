

import com.azure.storage.blob.*;
import com.azure.storage.blob.models.*;
import java.io.*;



public class App {
    public static void main(String[] args) throws IOException {

        System.out.println( "Hello World!" );
        String connectStr = "example-connect-str";

        // Bir container client oluşturmak için kullanılacak bir BlobServiceClient nesnesi oluşturulur
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(connectStr).buildClient();

        //Unique bir container ismi bulunur
        String containerName = "quickstartblobs2";

        //Container olusturulur ve sonrasinda bir container client nesnesi döndürülür
        BlobContainerClient containerClient = blobServiceClient.createBlobContainer(containerName);

        // Test icin localde data adlı bir dizin olusturulur(optin)
        String localPath = "./data/";
        String fileName = "quickstart" + java.util.UUID.randomUUID() + ".txt";
        File localFile = new File(localPath + fileName);

        // Dosya icerigi yazdirilir(optin)
        FileWriter writer = new FileWriter(localPath + fileName, true);
        writer.write("Hello, World!");
        writer.close();

        // Blob refernas linki cagrilir(optin)
        BlobClient blobClient = containerClient.getBlobClient(fileName);
        System.out.println("\nUploading to Blob storage as blob:\n\t" + blobClient.getBlobUrl());

        // Blob guncellenir
        blobClient.uploadFromFile(localPath + fileName);
        System.out.println("\nListing blobs...");


        // Blob belirtilen konuma indirilir
        String downloadFileName = fileName.replace(".txt", "DOWNLOAD.txt");
        File downloadedFile = new File(localPath + downloadFileName);
        System.out.println("\nDownloading blob to\n\t " +downloadedFile.getPath());
        blobClient.downloadToFile(downloadedFile.getPath());

    }
}
