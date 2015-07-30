package br.com.calculareceita.mb;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@RequestScoped
public class FileUploadMB {
	
	public FileUploadMB(){
	
	}
	
	public void doUpload(FileUploadEvent fileUploadEvent){
		
		UploadedFile uploadedFile = fileUploadEvent.getFile();
		String fileNameUploaded = uploadedFile.getFileName();
		long fileSizeUploaded = uploadedFile.getSize();
		String infoAboutFile = "<br/> Arquivo recebido: <b>"
				+fileNameUploaded + "</b><br/>"  
				+"Tamanho do Arquivo:<b>"+ fileSizeUploaded+"</b>";
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, new FacesMessage("Sucesso",infoAboutFile));
		
	}
}
