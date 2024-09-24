def sieve(upper_limit):
    '''
    Returns the highest prime number less than or equal to the provided limit.

            Parameters:
                    upper_limit (int): The upper bound (inclusive) for prime number search.

            Returns:
                    int: An integer that is the highest prime number less than or equal to the provided limit.
    '''
    # Create a list of booleans with all entries set as true initially
    primes = [True]*(upper_limit + 1)

    #Create values

    # 0 and 1 are not prime numbers, so mark them as False
    primes[0] = primes[1] = False

    # Loop over the list, marking non-prime numbers as False
    for (i, isprime) in enumerate(primes):
        if isprime:
            last_prime = i #stores the last prime found
            #Set the multiples of prime number as false
            for n in range(i**2, upper_limit + 1, i):
                primes[n] = False
    
    return last_prime


if __name__ == "__main__":
     # user input for the upper limit
    upper_limit = input("Upper limit: ")
    # Print the highest prime number up to the upper limit
    print(f"Highest prime up to (and including) limit: {sieve(int(upper_limit))}")