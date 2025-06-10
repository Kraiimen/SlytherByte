package com.slytherin.slytherbyte.dtos;

import com.slytherin.slytherbyte.models.entities.Language;

public record LanguageDto(int languageId, String name) {

    public static LanguageDto toDto(Language language){
        return new LanguageDto(language.getLanguageId(), language.getName());
    }

    public Language toEntity(){
        return new Language(this.languageId, this.name);
    }
}
