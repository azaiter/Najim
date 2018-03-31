package org.najim;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import org.najim.compiler.Compiler;

/**
 * <p>An implementation of {@code FileVisitor} that compiles source files.</p>
 * 
 * @author Brendan Jones
 * @author Abdulrahman Zaiter
 *
 */
public class SourceFileVisitor implements FileVisitor<Path> {

	/**
	 * <p>The {@code Compiler} to pass source files to.</p>
	 */
	private Compiler compiler;
	
	/**
	 * <p>The file extension for source files.</p>
	 * 
	 * TODO Probably move this into the {@code CompilerContext}.
	 */
	private String extension;
	
	/**
	 * <p>The path to write compiled files to.</p>
	 *
	 */
	private Path destDir;
	
	public SourceFileVisitor(Compiler compiler, String extension, Path destDir) {
		this.compiler = checkNotNull(compiler, "Compiler cannot be null.");
		this.extension = "." + checkNotNull(extension, "Extension cannot be null.").toLowerCase();
		this.destDir = checkNotNull(destDir, "Destination directory cannot be null.");
	}
	
	@Override
	public FileVisitResult postVisitDirectory(Path file, IOException e) throws IOException {
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult preVisitDirectory(Path file, BasicFileAttributes attr) throws IOException {
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attr) throws IOException {		
		String name = file.getFileName().toString();
		
		//Not a source file so we can ignore it.
		if(!name.toLowerCase().endsWith(extension)) {
			return FileVisitResult.CONTINUE;
		}
		
		name = name.substring(0, name.length() - extension.length()) + ".txt";
		
		try {
			System.out.println("Compiling source file (" + file + ")...");
			
			Path destFile = destDir.resolve(name);
			compiler.compile(file, destFile);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Path file, IOException e) throws IOException {
		return FileVisitResult.CONTINUE;
	}

}
