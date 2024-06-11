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
import com.example.tried.auth.member.specific.SpecificOfferingStatement;
import com.example.tried.auth.member.specific.SpecificOfferingStatementResponse;
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
    AuthMemberResponse getMemberCredentials(LoginCredentials credentials) throws JsonProcessingException,NullPointerException;

    // Verify Phone Number Using OTP
    SMSResponse VerifyPhoneNumber(String recipient) throws JsonProcessingException,NullPointerException;

    // Reset Member Pin
    AuthMemberResetResponse resetMemberPin(Payload payload) throws JsonProcessingException,NullPointerException;

    // Member Registration
    AuthMemberRegistrationResponse registerMember(MemberRegister register) throws JsonProcessingException,NullPointerException;

    // Member Registration Update
    AuthMemberRegistrationResponse updateRegisterMember(AuthMemberRegister register) throws JsonProcessingException,NullPointerException;

    // Member Personnel Login
    MemberPersonnelResponse loginMemberPersonnel(MemberPersonnel personnel) throws JsonProcessingException,NullPointerException;

    // Member Personnel Password Reset
    PersonnelResetResponse resetPersonnelPassword(MemberPersonnelReset reset) throws JsonProcessingException,NullPointerException;

    // Valid OTP
    // Member Personnel Password Reset
    String validateOTPPassword(OtpValidationRequest otpValidationRequest) throws NullPointerException;

    int getOTPPin();


    // Fetch Member Details
    MemberProfileResponse getMemberDetails(MemberProfile profile) throws JsonProcessingException, NullPointerException;

    // Get the Member Statement
    MemberOfferingResponse getMemberOffering(MemberOffering offering) throws JsonProcessingException, NullPointerException;

    // Get the Member Church Details
    RequestChurchDetailsResponse getMemberChurchDetails(RequestChurchDetails request) throws JsonProcessingException, NullPointerException;

    // Get the Member Transfer Details
    MemberTransferResponse getMemberTransfer(MemberTransfer transfer) throws JsonProcessingException, NullPointerException;

    // Get Member Details
    RequestMemberDetailsResponse getFullMemberDetails(RequestMemberDetails request) throws JsonProcessingException, NullPointerException;

    // Request Church Details with Church Code
    RequestChurchDetailsWithCodeResponse getChurchCodeDetails(RequestChurchDetailsWithCode requestChurchDetails) throws JsonProcessingException, NullPointerException;


    // Member Registration Update Details
    MemberRegisterUpdateResponse getMemberRegistrationUpdate(MemberRegistrationUpdate registrationUpdate) throws JsonProcessingException, NullPointerException;


    // List Church Members
    ListMembersResponse getChurchMembers(ListMembers members) throws JsonProcessingException, NullPointerException;

    // List Deactivated Church Members
    ListDeactivatedMembersResponse getDeactivatedMembers(ListDeactivatedMembers members) throws JsonProcessingException, NullPointerException;

    // List Payment Accounts
    LocalChurchPaymentAccountsResponse getPaymentAccounts(LocalChurchPaymentAccounts paymentAccount) throws JsonProcessingException, NullPointerException;

    // Get the Local Church Trust Fund Summary
    LocalChurchTrustFundSummaryResponse getLocalChurchTrustFundSummary(LocalChurchTrustFundSummary trustFundSummary) throws JsonProcessingException, NullPointerException;

    // Mobile Initiate Home Church Payment
    ChurchPaymentResponse getHomeChurchPayment(HomeChurchPayment homePayment) throws JsonProcessingException, NullPointerException;

    // Host Church Payment
    ChurchPaymentResponse getHostChurchPayment(HostChurchPayment hostChurchPayment) throws JsonProcessingException, NullPointerException;

    // Mobile Receive Funds Live
    MobileReceiveFundsResponse receiveMemberFunds(MobileReceiveFundsGiving giving) throws JsonProcessingException, NullPointerException;


    // MPESA STK Push Response
    MpesaSTKRequestResponse getMPESASTKResponse(MpesaSTKRequest request) throws JsonProcessingException, NullPointerException;


    // Transaction Tracing Summary Funds
    LocalChurchTransactionTracingResponse getTransactionTracingSummary(LocalChurchTransactionTracing transactionTracing) throws JsonProcessingException, NullPointerException;

    // Get the Local Church Non Trust Fund Summary
    LocalChurchNonTrustSummaryResponse getLocalChurchNonTrustFund(LocalChurchNonTrustSummary nonTrustSummary) throws JsonProcessingException, NullPointerException;


    // Get the Local Church Offering Reports
    LocalChurchOfferingSummaryResponse getLocalChurchOfferingReports(LocalChurchOfferingSummary churchOfferingSummary) throws JsonProcessingException, NullPointerException;


    // Get the Specific Offering Statement
    SpecificOfferingStatementResponse getSpecificOfferingStatement(SpecificOfferingStatement statement) throws JsonProcessingException, NullPointerException;
}
