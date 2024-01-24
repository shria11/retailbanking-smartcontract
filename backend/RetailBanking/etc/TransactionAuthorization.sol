// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract TransactionAuthorization {

    address public authorizedAddress;

    // Event emitted when a transaction is authorized
    event TransactionAuthorized(address indexed initiator, uint256 amount);

    // Modifier to check if the sender is authorized
    modifier onlyAuthorized() {
        require(msg.sender == authorizedAddress, "Not authorized");
        _;
    }

    // Constructor - set the initial authorized address
    constructor(address _initialAuthorizedAddress) {
        authorizedAddress = _initialAuthorizedAddress;
    }

    // Function to change the authorized address (can only be called by the current authorized address)
    function changeAuthorizedAddress(address _newAuthorizedAddress) public onlyAuthorized {
        authorizedAddress = _newAuthorizedAddress;
    }

    // Function to authorize a transaction (can only be called by the authorized address)
    function authorizeTransaction(uint256 _amount) public onlyAuthorized {
        // Perform transaction logic here (e.g., transfer funds, execute some action)
        // For this example, let's emit an event to indicate that the transaction is authorized.
        emit TransactionAuthorized(msg.sender, _amount);
    }
}