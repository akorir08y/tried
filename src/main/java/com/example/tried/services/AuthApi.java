package com.example.tried.services;


import com.example.tried.auth.dashboard.ListMembers;
import com.example.tried.auth.dashboard.ListMembersResponse;
import com.example.tried.auth.dashboard.deactivated.ListDeactivatedMembers;
import com.example.tried.auth.dashboard.deactivated.ListDeactivatedMembersResponse;
import com.example.tried.auth.dashboard.payment.LocalChurchPaymentAccounts;
import com.example.tried.auth.dashboard.payment.LocalChurchPaymentAccountsResponse;
import com.example.tried.auth.dashboard.trust_funds.LocalChurchTrustFundSummary;
import com.example.tried.auth.dashboard.trust_funds.LocalChurchTrustFundSummaryResponse;
import com.example.tried.auth.dto.*;
import com.example.tried.auth.dto.Payload;
import com.example.tried.auth.financial.MemberOffering;
import com.example.tried.auth.financial.MemberOfferingResponse;
import com.example.tried.auth.member.*;
import com.example.tried.auth.member.giving.*;
import com.example.tried.auth.personnel.MemberPersonnel;
import com.example.tried.auth.personnel.MemberPersonnelReset;
import com.example.tried.auth.personnel.MemberPersonnelResponse;
import com.example.tried.auth.personnel.PersonnelResetResponse;
import com.example.tried.auth.personnel.reports.non_trust_funds.LocalChurchNonTrustSummary;
import com.example.tried.auth.personnel.reports.non_trust_funds.LocalChurchNonTrustSummaryResponse;
import com.example.tried.auth.personnel.reports.offering.LocalChurchOfferingSummary;
import com.example.tried.auth.personnel.reports.offering.LocalChurchOfferingSummaryResponse;
import com.example.tried.auth.personnel.tracing.LocalChurchTransactionTracing;
import com.example.tried.auth.personnel.tracing.LocalChurchTransactionTracingResponse;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONObject;

public interface AuthApi {

    // Login as a Member Credentials
    AuthMemberResponse getMemberCredentials(LoginCredentials credentials);

    // Verify Phone Number Using OTP
    public SMSResponse VerifyPhoneNumber(String recipient);

    // Reset Member Pin
    AuthMemberResetResponse resetMemberPin(Payload payload);

    // Member Registration
    AuthMemberRegistrationResponse registerMember(MemberRegister register);

    // Member Registration Update
    AuthMemberRegistrationResponse updateRegisterMember(AuthMemberRegister register);

    // Member Personnel Login
    MemberPersonnelResponse loginMemberPersonnel(MemberPersonnel personnel);

    // Member Personnel Password Reset
    PersonnelResetResponse resetPersonnelPassword(MemberPersonnelReset reset);

    // Valid OTP
    // Member Personnel Password Reset
    public String validateOTPPassword(OtpValidationRequest otpValidationRequest);

    public int getOTPPin();


    // Fetch Member Details
    public MemberProfileResponse getMemberDetails(MemberProfile profile);

    // Get the Member Statement
    public MemberOfferingResponse getMemberOffering(MemberOffering offering);

    // Get the Member Church Details
    public RequestChurchDetailsResponse getMemberChurchDetails(RequestChurchDetails request);

    // Get the Member Transfer Details
    public MemberTransferResponse getMemberTransfer(MemberTransfer transfer);

    // Get Member Details
    public RequestMemberDetailsResponse getFullMemberDetails(RequestMemberDetails request);

    // Request Church Details with Church Code
    public RequestChurchDetailsWithCodeResponse getChurchCodeDetails(RequestChurchDetailsWithCode requestChurchDetails);


    // Member Registration Update Details
    public MemberRegisterUpdateResponse getMemberRegistrationUpdate(MemberRegistrationUpdate registrationUpdate);


    // List Church Members
    public ListMembersResponse getChurchMembers(ListMembers members);

    // List Deactivated Church Members
    public ListDeactivatedMembersResponse getDeactivatedMembers(ListDeactivatedMembers members);

    // List Payment Accounts
    public LocalChurchPaymentAccountsResponse getPaymentAccounts(LocalChurchPaymentAccounts paymentAccount);

    // Get the Local Church Trust Fund Summary
    public LocalChurchTrustFundSummaryResponse getLocalChurchTrustFundSummary(LocalChurchTrustFundSummary trustFundSummary);

    // Mobile Initiate Home Church Payment
    public ChurchPaymentResponse getHomeChurchPayment(HomeChurchPayment homePayment);

    // Host Church Payment
    public ChurchPaymentResponse getHostChurchPayment(HostChurchPayment hostChurchPayment);

    // Mobile Receive Funds Live
    public MobileReceiveFundsResponse receiveMemberFunds(MobileReceiveFundsGiving giving) throws JsonParseException;


    // MPESA STK Push Response
    public MpesaSTKRequestResponse getMPESASTKResponse(MpesaSTKRequest request);


    // Transaction Tracing Summary Funds
    public LocalChurchTransactionTracingResponse getTransactionTracingSummary(LocalChurchTransactionTracing transactionTracing);

    // Get the Local Church Non Trust Fund Summary
    public LocalChurchNonTrustSummaryResponse getLocalChurchNonTrustFund(LocalChurchNonTrustSummary nonTrustSummary);


    // Get the Local Church Offering Reports
    public LocalChurchOfferingSummaryResponse getLocalChurchOfferingReports(LocalChurchOfferingSummary churchOfferingSummary);
}
