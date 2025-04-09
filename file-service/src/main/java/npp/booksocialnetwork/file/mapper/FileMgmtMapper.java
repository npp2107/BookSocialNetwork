package npp.booksocialnetwork.file.mapper;

import npp.booksocialnetwork.file.dto.FileInfo;
import npp.booksocialnetwork.file.entity.FileMgmt;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FileMgmtMapper {
    @Mapping(target = "id", source = "name")
    FileMgmt toFileMgmt(FileInfo fileInfo);
}