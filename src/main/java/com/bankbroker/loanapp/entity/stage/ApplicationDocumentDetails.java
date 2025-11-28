package com.bankbroker.loanapp.entity.stage;

import com.bankbroker.loanapp.entity.LoanApplication;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "application_document_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationDocumentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false, unique = true)
    private LoanApplication application;

    @OneToMany(mappedBy = "documentDetails", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ApplicationUploadedDocument> documents = new ArrayList<>();
}
