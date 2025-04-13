package npp.booksocialnetwork.book.dto.enums;

import lombok.Getter;

@Getter
public enum ProposalStatusCode {
    PENDING(1001),
    APPROVED(1002),
    DECLINED(1003),
    ;

    ProposalStatusCode(int value) {
        this.value = value;
    }

    private final int value;
}