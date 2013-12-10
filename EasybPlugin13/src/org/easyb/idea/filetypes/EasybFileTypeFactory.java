package org.easyb.idea.filetypes;

import com.intellij.openapi.fileTypes.*;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.nio.charset.Charset;

public class EasybFileTypeFactory extends FileTypeFactory {

	class EasybFileType implements FileType {

		private final String name;

		EasybFileType(String name) {

			this.name = name;
		}

		@NotNull
		@Override
		public String getName() {
			return "easy " + name;
		}

		@NotNull
		@Override
		public String getDescription() {
			return getName();
		}

		@NotNull
		@Override
		public String getDefaultExtension() {
			return name;
		}

		@Override
		public Icon getIcon() {
			return new ImageIcon(getClass().getResource("/easyb.png"));
		}

		@Override
		public boolean isBinary() {
			return false;
		}

		@Override
		public boolean isReadOnly() {
			return false;
		}

		@Override
		public String getCharset(@NotNull VirtualFile file, byte[] content) {
			return Charset.defaultCharset().name();
		}
	}


	class EasybFileNameMatcher implements FileNameMatcher {
		private final String name;

		EasybFileNameMatcher(String name) {
			this.name = name;
		}

		@Override
		public boolean accept(@NonNls @NotNull String fileName) {
			return fileName.endsWith("." + name);
		}

		@NotNull
		@Override
		public String getPresentableString() {
			return "easyb " + name;
		}
	}

	@Override
	public void createFileTypes(@NotNull FileTypeConsumer consumer) {
		consumer.consume(new EasybFileType("story"), new EasybFileNameMatcher("story"));
		consumer.consume(new EasybFileType("specification"), new EasybFileNameMatcher("specification"));
	}
}
