package com.ers.model;

import javax.persistence.*;

@Entity
@Table(name = "services")
public class Reimbursement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reimbursement_id")
	private int reimbursementId;
	@Column(name = "accepted")
	private boolean accepted;
	@Column(name = "amount")
	private float amount;
	@Column(name = "description")
	private String description;
	@Column(name = "reimbursement_type")
	private String reimbursementType;
	@Column(name = "resolve_time")
	private String resolveTime;
	@Column(name = "resolved")
	private boolean resolved;
	@Column(name = "submit_time")
	private String submitTime;
	@Column(name = "author_id")
	private int authorId;
	@Column(name = "resolved_id")
	private int resolvedId;
	
	public Reimbursement() {}
	
	public Reimbursement(int reimbursementId, boolean accepted, float amount, String description, String reimbursementType,
			String resolveTime, boolean resolved, String submitTime, int authorId, int resolvedId) {
		super();
		this.reimbursementId = reimbursementId;
		this.accepted = accepted;
		this.amount = amount;
		this.description = description;
		this.reimbursementType = reimbursementType;
		this.resolveTime = resolveTime;
		this.resolved = resolved;
		this.submitTime = submitTime;
		this.authorId = authorId;
		this.resolvedId = resolvedId;
	}

	public int getReimbursementId() {
		return reimbursementId;
	}

	public void setReimbursementId(int reimbursementId) {
		this.reimbursementId = reimbursementId;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReimbursementType() {
		return reimbursementType;
	}

	public void setReimbursementType(String reimbursementType) {
		this.reimbursementType = reimbursementType;
	}

	public String getResolveTime() {
		return resolveTime;
	}

	public void setResolveTime(String resolveTime) {
		this.resolveTime = resolveTime;
	}

	public boolean isResolved() {
		return resolved;
	}

	public void setResolved(boolean resolved) {
		this.resolved = resolved;
	}

	public String getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public int getResolvedId() {
		return resolvedId;
	}

	public void setResolvedId(int resolvedId) {
		this.resolvedId = resolvedId;
	}

	@Override
	public String toString() {
		return "Services [reimbursementId=" + reimbursementId + ", accepted=" + accepted + ", amount=" + amount
				+ ", description=" + description + ", reimbursementType=" + reimbursementType + ", resolveTime="
				+ resolveTime + ", resolved=" + resolved + ", submitTime=" + submitTime + ", authorId=" + authorId
				+ ", resolvedId=" + resolvedId + "]";
	}
	
}
